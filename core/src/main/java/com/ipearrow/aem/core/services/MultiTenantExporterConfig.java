package com.ipearrow.aem.core.services;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(name = "Multi-Tenant Exporter Configuration", description = "Configuration for multi-tenant Jackson exporter paths")
public @interface MultiTenantExporterConfig {

    @AttributeDefinition(
            name = "Tenant Paths",
            description = "List of root paths for which the exporter should be active"
    )
    String[] tenantPaths() default {"/content/ipe-arrow", "/content/abc-arrow"};
}
