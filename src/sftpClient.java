package com.abrefael.sftpclient;

import android.app.Activity;
import android.content.Context;
import com.google.appinventor.components.annotations.*;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.AndroidNonvisibleComponent;
import com.google.appinventor.components.runtime.ComponentContainer;
import com.google.appinventor.components.runtime.EventDispatcher;
import com.jcraft.jsch.*;

@DesignerComponent(
        version = 1,
        description = "A simple SFTP Client for AI2",
        category = ComponentCategory.EXTENSION,
        nonVisible = true,
        iconName = "https://drive.google.com/file/d/1Np6hyRE1cf6T2Yb2Rsrqn9ZNbtj9_2oO/view?usp=drive_link")

@SimpleObject(external = true)
//Libraries
@UsesLibraries(libraries = "jsch-0.1.55.jar")
//Permissions
@UsesPermissions(permissionNames = "android.permission.INTERNET")

public class sftpClient extends AndroidNonvisibleComponent {

    //Activity and Context
    private Context context;
    private Activity activity;
    private Session session;
    private ChannelSftp channelSftp;

    public SFTPClient(ComponentContainer container){
        super(container.$form());
        this.activity = container.$context();
        this.context = container.$context();
    }
    @SimpleFunction(description = "Sample Function Generated by Niotron")
    public void connect(String host, int port, String username, String password){
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

    @SimpleFunction(description = "Test Event Generated by Niotron")
    public void uploadFile(String localFilePath, String remoteFilePath){
        try {
            channelSftp.put(localFilePath, remoteFilePath);
            return true;
        } catch (SftpException e) {
            e.printStackTrace();
            return false;
        }
    }
}