package com.ipearrow.aem.core.models;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;

@Model(
    adaptables = {SlingHttpServletRequest.class, Resource.class},
    defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class ThemeResolverModel {

    @SlingObject
    private Resource currentResource;

    @SlingObject
    private ResourceResolver resourceResolver;

    private String theme;

    @PostConstruct
    protected void init() {
        if (currentResource == null || resourceResolver == null) {
            theme = "arrow";
            return;
        }

        PageManager pageManager = resourceResolver.adaptTo(PageManager.class);
        if (pageManager != null) {
            Page page = pageManager.getContainingPage(currentResource);
            theme = resolveTheme(page);
        } else {
            theme = resolveThemeFromPath(currentResource.getPath());
        }
    }

    private String resolveTheme(Page page) {
        Page currentPage = page;
        while (currentPage != null) {
            Resource contentResource = currentPage.getContentResource();
            if (contentResource != null) {
                String t = contentResource.getValueMap().get("theme", String.class);
                if (StringUtils.isNotBlank(t) && !StringUtils.equalsIgnoreCase(t, "none")) {
                    return t;
                }
            }
            currentPage = currentPage.getParent();
        }
        // If not found in page properties, fall back to path
        if (page != null) {
            return resolveThemeFromPath(page.getPath());
        }
        return "arrow";
    }

    private String resolveThemeFromPath(String path) {
        if (StringUtils.isBlank(path)) {
            return "arrow";
        }
        if (path.contains("abc-arrow")) {
            return "abc";
        }
        if (path.contains("ipe-arrow")) {
            return "ipe";
        }
        if (path.contains("arrow")) {
            return "arrow";
        }
        return "arrow";
    }

    public String getTheme() {
        return theme;
    }
}
