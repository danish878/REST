package com.danny.rest.service;

import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;
import java.io.*;
import java.util.List;

@Service
public class FileServiceImpl implements FileService {

    private static final String FILE_PATH = "C:\\Users\\Danish\\Desktop\\REST_uploads\\uploaded.jpg";

    @Override
    public void upload(List<Attachment> attachments) throws IOException {
        for (Attachment attachment : attachments) {
            copyFile(attachment.getDataHandler().getInputStream());
        }
    }

    @Override
    public Response download() {
        File file = new File(FILE_PATH);

        return Response.ok(file)
                .header("Content-Disposition", "attachment; filename=\"downloaded.jpg\"")
                .build();
    }

    private void copyFile(InputStream inputStream) throws IOException {
        OutputStream out = new FileOutputStream(new File(FILE_PATH));
        int read;
        byte[] bytes = new byte[1024];

        while ((read = inputStream.read(bytes)) != -1) {
            out.write(bytes, 0, read);
        }
        out.flush();
        out.close();
    }

}