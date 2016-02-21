package demo

import grails.test.mixin.TestFor
import grails.test.mixin.TestMixin
import grails.test.mixin.hibernate.HibernateTestMixin
import spock.lang.Specification
import spock.lang.Stepwise

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(DemoController)
@TestMixin(HibernateTestMixin)
@Stepwise
class DemoControllerSpec extends Specification {

    void setupSpec() {
        hibernateDomain([Person])
    }

    void "test creating person"() {
        when:
        request.addHeader 'Accept', 'application/json'
        request.method = 'POST'
        request.json = '{"name":"original name"}'
        controller.create()

        then:
        response.status == 200
        response.text.contains '"original name"'
    }

    void "test retrieving person"() {
        when:
        request.addHeader 'Accept', 'application/json'
        params.id = 1
        controller.show()

        then:
        response.status == 200
        response.text.contains '"original name"'
    }

    void "test auto bind"() {
        when:
        request.addHeader 'Accept', 'application/json'
        request.method = 'PUT'
        request.json = '{"name":"first new name"}'
        params.id = 1
        controller.autoBind()

        then:
        response.status == 200
        response.text.contains '"first new name"'
    }

    void "test retrieving person after auto bind"() {
        when:
        request.addHeader 'Accept', 'application/json'
        params.id = 1
        controller.show()

        then:
        response.status == 200
        response.text.contains '"first new name"'
    }

    void "test manual bind"() {
        when:
        request.addHeader 'Accept', 'application/json'
        request.method = 'PUT'
        request.json = '{"name":"second new name"}'
        params.id = 1
        controller.manualBind()

        then:
        response.status == 200
        response.text.contains '"second new name"'
    }

    void "test retrieving person after manual bind"() {
        when:
        request.addHeader 'Accept', 'application/json'
        params.id = 1
        controller.show()

        then:
        response.status == 200
        response.text.contains '"second new name"'
    }

    void "test manual bind read only"() {
        when:
        request.addHeader 'Accept', 'application/json'
        request.method = 'PUT'
        request.json = '{"name":"third new name"}'
        params.id = 1
        controller.manualBindReadOnly()

        then:
        response.status == 200
        response.text.contains '"third new name"'
    }

    void "test retrieving person after manual bind read only"() {
        when:
        request.addHeader 'Accept', 'application/json'
        params.id = 1
        controller.show()

        then:
        response.status == 200
        response.text.contains '"second new name"'
    }

    void "test auto bind read only"() {
        when:
        request.addHeader 'Accept', 'application/json'
        request.method = 'PUT'
        request.json = '{"name":"fourth new name"}'
        params.id = 1
        controller.autoBindReadOnly()

        then:
        response.status == 200
        response.text.contains '"fourth new name"'
    }

    void "test retrieving person after auto bind read only"() {
        when:
        request.addHeader 'Accept', 'application/json'
        params.id = 1
        controller.show()

        then:
        response.status == 200
        response.text.contains '"second new name"'
    }
}
