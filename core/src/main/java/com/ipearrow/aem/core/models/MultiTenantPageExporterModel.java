// package com.ipearrow.aem.core.models;

// import com.adobe.cq.export.json.ComponentExporter;
// import com.adobe.cq.export.json.ExporterConstants;
// import com.day.cq.wcm.api.Page;
// import com.day.cq.wcm.api.PageManager;
// import com.fasterxml.jackson.annotation.JsonProperty;
// import com.ipearrow.aem.core.services.MultiTenantExporterService;
// import org.apache.sling.api.SlingHttpServletRequest;
// import org.apache.sling.api.resource.Resource;
// import org.apache.sling.api.resource.ResourceResolver;
// import org.apache.sling.models.annotations.DefaultInjectionStrategy;
// import org.apache.sling.models.annotations.Exporter;
// import org.apache.sling.models.annotations.Model;
// import org.apache.sling.models.annotations.injectorspecific.OSGiService;
// import org.apache.sling.models.annotations.injectorspecific.SlingObject;

// import javax.annotation.PostConstruct;
// import java.util.ArrayList;
// import java.util.Iterator;
// import java.util.List;

// @Model(
//         adaptables = SlingHttpServletRequest.class,
//         adapters = {MultiTenantPageExporterModel.class, ComponentExporter.class},
//         resourceType = {MultiTenantPageExporterModel.RESOURCE_TYPE, "ipe-arrow/components/page"},
//         defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL,
//         ranking = 1500
// )
// @Exporter(
//         name = ExporterConstants.SLING_MODEL_EXPORTER_NAME,
//         extensions = ExporterConstants.SLING_MODEL_EXTENSION
// )
// public class MultiTenantPageExporterModel implements ComponentExporter {

//     protected static final String RESOURCE_TYPE = "ipe-arrow/components/multi-tenant-exporter";

//     @OSGiService
//     private MultiTenantExporterService tenantService;

//     @SlingObject
//     private Resource currentResource;

//     @SlingObject
//     private ResourceResolver resourceResolver;

//     private List<PageData> pages = new ArrayList<>();
//     private boolean allowed = false;
//     private String debugMessage = "MultiTenantPageExporterModel is Active";

//     @PostConstruct
//     protected void init() {
//         String path = currentResource.getPath();
//         if (tenantService.isAllowedPath(path)) {
//             allowed = true;
//             collectPages(path);
//         }
//     }

//     private void collectPages(String rootPath) {
//         PageManager pageManager = resourceResolver.adaptTo(PageManager.class);
//         if (pageManager != null) {
//             Page rootPage = pageManager.getPage(rootPath);
//             if (rootPage != null) {
//                 // Add the root page itself
//                 pages.add(new PageData(rootPage));
                
//                 // Recursively add all child pages
//                 Iterator<Page> it = rootPage.listChildren(null, true);
//                 while (it.hasNext()) {
//                     pages.add(new PageData(it.next()));
//                 }
//             }
//         }
//     }

//     @JsonProperty("tenantPaths")
//     public List<String> getConfiguredPaths() {
//         return tenantService.getTenantPaths();
//     }

//     @JsonProperty("isAllowed")
//     public boolean isAllowed() {
//         return allowed;
//     }

//     @JsonProperty("pages")
//     public List<PageData> getPages() {
//         return pages;
//     }

//     @JsonProperty("_debug")
//     public String getDebugMessage() {
//         return debugMessage;
//     }

//     @Override
//     public String getExportedType() {
//         return RESOURCE_TYPE;
//     }

//     /**
//      * Inner class to represent page data in JSON
//      */
//     public static class PageData {
//         private String title;
//         private String path;
//         private String name;
//         private String description;

//         public PageData(Page page) {
//             this.title = page.getTitle();
//             this.path = page.getPath();
//             this.name = page.getName();
//             this.description = page.getDescription();
//         }

//         @JsonProperty("pageTitle")
//         public String getTitle() {
//             return title;
//         }

//         @JsonProperty("pagePath")
//         public String getPath() {
//             return path;
//         }

//         @JsonProperty("pageName")
//         public String getName() {
//             return name;
//         }

//         @JsonProperty("pageDescription")
//         public String getDescription() {
//             return description;
//         }
//     }
// }
