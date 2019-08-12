package com.danny.restwsclient;

import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.apache.cxf.jaxrs.ext.multipart.ContentDisposition;

import javax.ws.rs.core.MediaType;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class FileClient {

    private static final String FILE_SERVICE_URL = "http://localhost:8080/cxf_rest_ws/services/fileService/upload";
    private static final String FILE_PATH = "C:\\Users\\Danish\\Desktop\\audi.jpg";

    public static void main(String[] args) throws FileNotFoundException {

        WebClient client = WebClient.create(FILE_SERVICE_URL);
        client.type(MediaType.MULTIPART_FORM_DATA);

        ContentDisposition cd = new ContentDisposition("attachment; filename=audi.jpg");
        FileInputStream fis = new FileInputStream(new File(FILE_PATH));
        Attachment attachment = new Attachment("root", fis, cd);

        client.post(attachment);

        client.close();
    }
}
