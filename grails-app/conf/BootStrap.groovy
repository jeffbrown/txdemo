class BootStrap {

    def init = { servletContext ->
        new demo.Person(name: 'Jeff').save()
    }
    def destroy = {
    }
}
