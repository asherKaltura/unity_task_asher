package annotations;
import enums.DataSource;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface TestData {
    String value();       // קובץ, query או sheet
    DataSource source();      // "CSV", "EXCEL", "JSON", "DB"
    String params() default ""; // key=value,key=value
    int limit() default -1;      // מספר שורות
    int sheetIndex() default 0;  // רק ל-Excel, גיליון ברירת מחדל 0
}
