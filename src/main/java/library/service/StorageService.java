package library.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * Upload file
 */
public interface StorageService {

    /**
     *  Загрузка файла на сервер
     * @param upFile - файл
     * @return путь сохранения файла
     * @throws Exception
     */
    String uploadFile(MultipartFile upFile) throws Exception;

    void deletePath(String path);
}
