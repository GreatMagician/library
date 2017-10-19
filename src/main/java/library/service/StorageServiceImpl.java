package library.service;

import library.util.exceptoins.ContentTypeException;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.file.Paths;

/**
 * работа с Файлами, сохранение загруженного файла
 */
@Service
public class StorageServiceImpl implements StorageService{
    private static final String ROOT_FOLDER = ".\\src\\main\\webapp\\";
    private static final String UPLOADED_FOLDER = "resources\\img";

    @Override
    public String uploadFile(MultipartFile upFile) throws Exception {
        String filename = upFile.getOriginalFilename();
        String filepath = Paths.get(ROOT_FOLDER + UPLOADED_FOLDER, filename).toString();
        String contentType = upFile.getContentType();
        if (contentType.equals("image/jpeg") || contentType.equals("image/png")){
            // Save the file locally
            try (BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filepath)))) {
                stream.write(upFile.getBytes());
            }
        }else
            throw new ContentTypeException("Неправильный тип файла. Разрешённый тип - jpg, png");
         String result = Paths.get(UPLOADED_FOLDER, filename).toString();
         return result.replace("\\", "/");
    }

    @Override
    public void deletePath(String path) {
        if (!path.isEmpty()) {
            String fileName = Paths.get(path).getFileName().toString();
            File file = new File(ROOT_FOLDER + UPLOADED_FOLDER + "\\" + fileName);
            if (file.exists()) file.delete();
        }
    }

}
