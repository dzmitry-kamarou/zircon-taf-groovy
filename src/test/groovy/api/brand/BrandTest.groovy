package api.brand

import app.api.flow.BrandFlow
import business.brand.Brand
import business.brand.BrandFactory
import org.hamcrest.Matchers
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test

import static org.hamcrest.MatcherAssert.assertThat
import static org.hamcrest.Matchers.allOf
import static org.hamcrest.Matchers.hasItem
import static org.hamcrest.Matchers.is

class BrandTest {

    @Test
    @Tag('C8')
    @Tag('smoke')
    @Tag('regression')
    void brandsCanBeRetrieved() {
        def brand = BrandFactory.randomBrand()
        def brandFlow = new BrandFlow()
        try {
            brandFlow.createBrand(brand)
            def brands = brandFlow.getBrands()
            def reason = "Exists '${brand.name}' brand is in brands list"
            assertThat(reason, brands, hasItem(allOf(
                    Matchers.<Brand> hasProperty('id', is(brand.id)),
                    Matchers.<Brand> hasProperty('name', is(brand.name))
            )))
        } finally {
            brandFlow.deleteBrand brandFlow.findBrand(brand)
        }
    }

    @Test
    @Tag('C9')
    @Tag('smoke')
    @Tag('regression')
    void brandCanBeAdded() {
        def brand = BrandFactory.randomBrand()
        BrandFlow brandFlow = new BrandFlow()
        try {
            brandFlow.createBrand brand
            def expected = brandFlow.findBrand brand
            def reason = "Brand '${brand.name}' was added"
            assertThat(reason, expected, allOf(
                    Matchers.hasProperty('id', is(brand.id)),
                    Matchers.hasProperty('name', is(brand.name))))
        } finally {
            brandFlow.deleteBrand brand
        }
    }
}
