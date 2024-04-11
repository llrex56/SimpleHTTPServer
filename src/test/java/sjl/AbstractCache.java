package sjl;

import cn.hutool.core.util.ReflectUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author luozhenhong
 * @version 1.0
 * 2024/3/25 14:20
 */
@Slf4j
public abstract class AbstractCache<T> {

    private Map<String, T> cacheMap;

    public AbstractCache() {
        initializeCache();
    }

    public Optional<T> get(String key) {
        return Optional.ofNullable(cacheMap.get(key));
    }

    protected synchronized void initializeCache() {
        String filePath = System.getProperty("user.dir") + File.separator + getFileRelativePath();

        try (ExcelReader excelReader = ExcelUtil.getReader(filePath, getExcelSheetIndex())) {
            @SuppressWarnings("unchecked")
            Class<T> clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

            final Map<String, String> excelHeaderAlias = getExcelHeaderAlias(clazz);

            excelReader.setHeaderAlias(excelHeaderAlias);

            final long startTime = System.currentTimeMillis();

            final List<T> beanList = excelReader.readAll(clazz);

            if (log.isDebugEnabled()) {
                log.debug("Initialize cache, file path: {}, header alias: {}, complete, duration: {}ms", getFileRelativePath(), excelHeaderAlias, System.currentTimeMillis() - startTime);
            }
            cacheMap = beanList.stream().collect(Collectors.toMap(this::getKey, Function.identity(), (v1, v2) -> v1));

        } catch (Exception e) {
            log.error("Failed to read data from Excel file: {}", e.getMessage());
            throw new RuntimeException("Failed to read data from excel file.", e);
        }
    }

    private Map<String, String> getExcelHeaderAlias(Class<T> clazz) {
        final Field[] fields = ReflectUtil.getFields(clazz, field -> field.isAnnotationPresent(ExcelAlias.class));

        if (fields == null || fields.length <= 0) {
            return Collections.emptyMap();
        }
        return Arrays.stream(fields).collect(Collectors.toMap(field -> field.getAnnotation(ExcelAlias.class).value(), Field::getName, (v1, v2) -> v1));
    }

    protected abstract String getKey(T item);

    protected abstract int getExcelSheetIndex();

    protected abstract String getFileRelativePath();
}