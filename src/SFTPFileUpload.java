package com.abrefael.sftpfileupload;

import android.app.Activity;
import android.content.Context;
import com.google.appinventor.components.annotations.*;
import com.google.appinventor.components.common.*;
import com.google.appinventor.components.runtime.*;
import com.jcraft.jsch.*;

@DesignerComponent(
        version = 1,
        description = "An SFTP client for uploading files to an SSH-enabled server.",
        category = ComponentCategory.EXTENSION,
        nonVisible = true,
        iconName = "https://drive.google.com/file/d/1Np6hyRE1cf6T2Yb2Rsrqn9ZNbtj9_2oO/view?usp=sharing")
@SimpleObject(external = true)
@UsesLibraries(libraries = "jsch-0.1.55.jar")
@UsesPermissions(permissionNames = "android.permission.INTERNET")
public class SFTPFileUpload extends AndroidNonvisibleComponent {

    private Session session;
    private ChannelSftp channelSftp;

    public SFTPFileUpload(Form form) {
        super(form);
    }

    public boolean connect(String host, int port, String username, String password) {
        try {
            JSch jsch = new JSch();
            session = jsch.getSession(username, host, port);
            session.setPassword(password);
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();
            channelSftp = (ChannelSftp) session.openChannel("sftp");
            channelSftp.connect();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean uploadFile(String localFilePath, String remoteFilePath) {
        try {
            channelSftp.put(localFilePath, remoteFilePath);
            return true;
        } catch (SftpException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void disconnect() {
        if (channelSftp != null) {
            channelSftp.disconnect();
        }
        if (session != null) {
            session.disconnect();
        }
    }
}
