package org.example.data;

import org.example.model.SomeObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;

import javax.sql.DataSource;
import java.util.List;

public class MyDaoImpl extends JdbcTemplate {

    public MyDaoImpl(DataSource dataSource) {
        super(dataSource);
    }



    public String  getSomeObjects() {
        String sql = "SELECT text FROM mydb.some_object so where so.id = 1";
        return queryForObject(sql, String.class);
    }


}