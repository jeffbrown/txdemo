package demo

import grails.transaction.Transactional

@Transactional
class DemoController {

    def show(Person p) {
        respond p
    }

    def autoBind(Person p) {
        respond p
    }

    def manualBind() {
        Person p = Person.get(params.id)
        p.properties = request
        respond p
    }

    @Transactional(readOnly=true)
    def autoBindReadOnly(Person p) {
        respond p
    }

    @Transactional(readOnly=true)
    def manualBindReadOnly() {
        Person p = Person.get(params.id)
        p.properties = request
        respond p
    }
}
