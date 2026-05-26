package com.ipearrow.aem.core.services.impl;

import com.ipearrow.aem.core.services.MultiTenantExporterConfig;
import com.ipearrow.aem.core.services.MultiTenantExporterService;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.metatype.annotations.Designate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component(service = MultiTenantExporterService.class, immediate = true)
@Designate(ocd = MultiTenantExporterConfig.class)
public class MultiTenantExporterServiceImpl implements MultiTenantExporterService {

    private List<String> tenantPaths;

    @Activate
    @Modified
    protected void activate(MultiTenantExporterConfig config) {
        this.tenantPaths = Arrays.asList(config.tenantPaths());
    }

    @Override
    public List<String> getTenantPaths() {
        return new ArrayList<>(tenantPaths);
    }

    @Override
    public boolean isAllowedPath(String path) {
        if (path == null) return false;
        return tenantPaths.stream().anyMatch(path::startsWith);
    }
}
