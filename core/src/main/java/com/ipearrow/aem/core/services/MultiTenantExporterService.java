package com.ipearrow.aem.core.services;

import java.util.List;

public interface MultiTenantExporterService {
    List<String> getTenantPaths();
    boolean isAllowedPath(String path);
}
