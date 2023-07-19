package com.springtest.servicema.persistence_config;

import java.sql.SQLException;
import org.springframework.data.relational.core.mapping.event.BeforeConvertCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import com.springtest.servicema.dominio.Lote;

@Component
public class LoteGenerateIdCallback implements BeforeConvertCallback<Lote> {
 
    private final JdbcTemplate jdbcTemplate;
    private static String SEQUENCE_NAME = "id_lote_sq";

    public LoteGenerateIdCallback(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
 
    @Override
    public Lote onBeforeConvert(Lote lote) {
        if (lote.getId() == null) {
             
            Long id = jdbcTemplate.query("SELECT nextval('"+ SEQUENCE_NAME +"')",
                    rs -> {
                        if (rs.next()) {
                            return rs.getLong(1);
                        } else {
                            throw new SQLException("Unable to retrieve value from sequence "+SEQUENCE_NAME);
                        }
                    });
            lote.setId(id);
        }
 
        return lote;
    }
}
