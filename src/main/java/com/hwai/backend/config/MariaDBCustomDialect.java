package com.hwai.backend.config;

import org.hibernate.dialect.MariaDBDialect;
import org.hibernate.dialect.function.StandardSQLFunction;
import org.hibernate.type.StandardBasicTypes;

public class MariaDBCustomDialect extends MariaDBDialect {
    public MariaDBCustomDialect() {
        registerFunction("GROUP_CONCAT",
                new StandardSQLFunction("group_concat", StandardBasicTypes.STRING));
    }
}
