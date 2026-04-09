import burp.api.montoya.BurpExtension;
import burp.api.montoya.MontoyaApi;

import javax.swing.*;

public class Extension implements BurpExtension {
    @Override
    public void initialize(MontoyaApi montoyaApi) {
        montoyaApi.extension().setName("Burp Export History");

        // TODO Add your code here
        JPanel panel = new JPanel();
        panel.add(new JLabel("abc"));
        montoyaApi.userInterface().registerSuiteTab("asdasd", panel);
    }
}