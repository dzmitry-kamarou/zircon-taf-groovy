package app.api.flow

import app.api.service.BrandApiService
import business.brand.Brand

class BrandFlow {

    private final def brandApiService = new BrandApiService()

    Brand createBrand(Brand brand) {
        def brandCreated = brandApiService
                .postCreate(brand.name)
                .body()
                .as(Brand.class)
        if (brandCreated != null) {
            brand.id = brandCreated.id
            brand
        } else {
            null
        }
    }

    List<Brand> getBrands() {
        brandApiService
                .getAll()
                .jsonPath()
                .getList('', Brand.class)
    }

    void deleteBrand(Brand brand) {
        brandApiService.delete(brand.id)
    }

    Brand findBrand(Brand brand) {
        brandApiService
                .getFind(brand.name)
                .body()
                .as(Brand.class)
    }
}
