package com.springtest.servicema.persistence_config;

import java.sql.SQLException;

import org.springframework.data.relational.core.mapping.event.BeforeConvertCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.springtest.servicema.dominio.ItemEstoque;

@Component
public class ItemEstoqueGenerateIdCallback implements BeforeConvertCallback<ItemEstoque> {
       
    private final JdbcTemplate jdbcTemplate;
    private static String SEQUENCE_NAME = "id_item_estoque_sq";

    public ItemEstoqueGenerateIdCallback(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
 
    @Override
    public ItemEstoque onBeforeConvert(ItemEstoque obj) {
        if (obj.getId() == null) {
             
            Long id = jdbcTemplate.query("SELECT nextval('"+ SEQUENCE_NAME +"')",
                    rs -> {
                        if (rs.next()) {
                            return rs.getLong(1);
                        } else {
                            throw new SQLException("Unable to retrieve value from sequence "+SEQUENCE_NAME);
                        }
                    });
            obj.setId(id);
        }
 
        return obj;
    }

}
