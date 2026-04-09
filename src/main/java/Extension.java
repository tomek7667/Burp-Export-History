import burp.api.montoya.BurpExtension;
import burp.api.montoya.MontoyaApi;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;

public class Extension implements BurpExtension {
    @Override
    public void initialize(MontoyaApi montoyaApi) {
        montoyaApi.extension().setName("Burp Export History");

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel label = new JLabel("Please beware that ~50 requests might be ~3 MiB");
        label.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(Box.createVerticalStrut(10));
        var btn = new JButton("Save to a file");
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        btn.addActionListener(actionEvent -> {
            this.saveFullHistory(montoyaApi);
        });

        panel.add(Box.createVerticalGlue());
        panel.add(label);
        panel.add(Box.createVerticalStrut(10));
        panel.add(btn);
        panel.add(Box.createVerticalGlue());
        montoyaApi.userInterface().registerSuiteTab("Export Proxy History", panel);
    }

    public void saveFullHistory(MontoyaApi montoyaApi) {
        var arr = new JSONArray();
        var hist = montoyaApi.proxy().history();
        for (burp.api.montoya.proxy.ProxyHttpRequestResponse h : hist) {
            var obj = new JSONObject();
            obj.put("id", h.id());
            if (h.request() == null) {
                obj.put("request", (Object) null);
            } else {
                obj.put("request", h.request().toString());
            }

            if (h.response() == null) {
                obj.put("resposne", (Object) null);
            } else {
                obj.put("response", h.response().toString());
            }
            arr.put(obj);
        }
        var content = arr.toString();
        FileDialog fileDialog = new FileDialog(new Frame(), "Save", FileDialog.SAVE);
        fileDialog.setFilenameFilter((file, s) -> s.endsWith(".json"));
        var filename = String.format("proxy_history_%d.json", new Date().getTime());
        fileDialog.setFile(filename);
        fileDialog.setVisible(true);
        String dir = fileDialog.getDirectory();
        File oneFile = new File(dir + fileDialog.getFile());
        try {
            oneFile.createNewFile();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }

        FileOutputStream f = null;
        DataOutputStream h = null;
        try {
            f = new FileOutputStream(oneFile);
            h = new DataOutputStream(f);
            h.writeBytes(content);
        } catch (java.io.IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                h.close();
                f.close();
            } catch (java.io.IOException e) {
                e.printStackTrace();
            }
        }
    }
}
