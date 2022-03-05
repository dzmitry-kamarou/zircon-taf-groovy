package business.brand

import com.github.javafaker.Faker

class BrandFactory {

    private BrandFactory() {
    }

    static Brand randomBrand() {
        new Brand(name: new Faker().name().name())
    }
}
