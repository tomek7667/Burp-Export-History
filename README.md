# Burp Export History

A simple Burp Suite extension that exports proxy history to a JSON file.

## Features

- Adds a Burp tab for exporting proxy history
- Saves requests and responses into a JSON file
- Easy to build and load into Burp Suite

## Download

You can download the latest release JAR from the **GitHub Releases** page:

- https://github.com/tomek7667/Burp-Export-History/releases

Look for the attached `.jar` file in the latest release.

## Installation

1. Download the JAR file from the Releases page
2. Open Burp Suite
3. Go to **Extensions** -> **Installed**
4. Click **Add**
5. Select the downloaded JAR file
6. Load the extension

## Build from source

### Requirements

- Java 21
- Gradle

### Build

```bash
./gradlew clean jar
```

The built JAR will be available in:

```text
build/libs/
```

## Usage

1. Open the extension in Burp Suite
2. Go to the **Export Proxy History** tab
3. Click **Save to a file**
4. Choose where to save the exported `.json` file

## Output format

The export contains proxy history entries in JSON format, including:

- request ID
- request data
- response data

## Development

To make changes:

1. Edit the source code
2. Rebuild the JAR
3. Reload the extension in Burp Suite
