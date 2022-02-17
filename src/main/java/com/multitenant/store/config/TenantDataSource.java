package com.multitenant.store.config;

import com.multitenant.store.entities.DataSourceConfig;
import com.multitenant.store.repository.DataSourceConfigRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class TenantDataSource {

    private final HashMap<String, DataSource> dataSources = new HashMap<>();
    private final DataSourceConfigRepository configRepository;

    public DataSource getDataSource(String name) {
        if (dataSources.get(name) != null) {
            return dataSources.get(name);
        }

        DataSource dataSource = createDataSource(name);

        if (dataSource != null){
            dataSources.put(name, dataSource);
        }

        return dataSource;
    }

    @PostConstruct
    public Map<String, DataSource> getAll() {
        List<DataSourceConfig> configList = configRepository.findAll();
        Map<String, DataSource> result = new HashMap<>();

        for (DataSourceConfig config : configList) {
            DataSource dataSource = getDataSource(config.getName());
            result.put(config.getName(), dataSource);
        }

        return result;
    }

    private DataSource createDataSource(String name) {
        DataSourceConfig config = configRepository.findByName(name);

        if (config != null) {
            DataSourceBuilder<?> factory = DataSourceBuilder
                    .create().driverClassName(config.getDriverClassName())
                    .username(config.getUsername())
                    .password(config.getPassword())
                    .url(config.getUrl());
            return factory.build();
        }

        return null;
    }
}
