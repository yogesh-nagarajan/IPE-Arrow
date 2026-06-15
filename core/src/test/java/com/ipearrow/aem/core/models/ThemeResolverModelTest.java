package com.ipearrow.aem.core.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.apache.sling.api.resource.Resource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import com.day.cq.wcm.api.Page;
import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import com.ipearrow.aem.core.testcontext.AppAemContext;

@ExtendWith(AemContextExtension.class)
class ThemeResolverModelTest {

    private final AemContext context = AppAemContext.newAemContext();

    @BeforeEach
    public void setup() throws Exception {
    }

    @Test
    void testThemeFromPagePropertiesDirect() {
        Page page = context.create().page("/content/ipe-arrow/us/en/direct", "/conf/ipe-arrow/settings/wcm/templates/spa-page-template", "theme", "abc");
        Resource resource = page.getContentResource();
        context.request().setResource(resource);

        ThemeResolverModel model = context.request().adaptTo(ThemeResolverModel.class);
        assertNotNull(model);
        assertEquals("abc", model.getTheme());
    }

    @Test
    void testThemeInheritanceFromParent() {
        Page parentPage = context.create().page("/content/ipe-arrow/us/en", "/conf/ipe-arrow/settings/wcm/templates/spa-page-template", "theme", "ipe");
        Page childPage = context.create().page(parentPage, "child", "/conf/ipe-arrow/settings/wcm/templates/spa-page-template");
        Resource resource = childPage.getContentResource();
        context.request().setResource(resource);

        ThemeResolverModel model = context.request().adaptTo(ThemeResolverModel.class);
        assertNotNull(model);
        assertEquals("ipe", model.getTheme());
    }

    @Test
    void testPathFallbackAbc() {
        Page page = context.create().page("/content/abc-arrow/us/en/home");
        Resource resource = page.getContentResource();
        context.request().setResource(resource);

        ThemeResolverModel model = context.request().adaptTo(ThemeResolverModel.class);
        assertNotNull(model);
        assertEquals("abc", model.getTheme());
    }

    @Test
    void testPathFallbackIpe() {
        Page page = context.create().page("/content/ipe-arrow/us/en/home");
        Resource resource = page.getContentResource();
        context.request().setResource(resource);

        ThemeResolverModel model = context.request().adaptTo(ThemeResolverModel.class);
        assertNotNull(model);
        assertEquals("ipe", model.getTheme());
    }

    @Test
    void testPathFallbackArrow() {
        Page page = context.create().page("/content/arrow-site/us/en/home");
        Resource resource = page.getContentResource();
        context.request().setResource(resource);

        ThemeResolverModel model = context.request().adaptTo(ThemeResolverModel.class);
        assertNotNull(model);
        assertEquals("arrow", model.getTheme());
    }
}
