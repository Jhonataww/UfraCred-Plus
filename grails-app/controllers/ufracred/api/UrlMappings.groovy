package ufracred.api

class UrlMappings {

    static mappings = {
        delete "/$controller/$id(.$format)?"(action:"delete")
        get "/$controller(.$format)?"(action:"index")
        get "/$controller/$id(.$format)?"(action:"show")
        post "/$controller(.$format)?"(action:"save")
        put "/$controller/$id(.$format)?"(action:"update")
        patch "/$controller/$id(.$format)?"(action:"patch")

        "/"(controller: 'application', action:'index')
        "500"(view: '/error')
        "404"(view: '/notFound')

        "/simulaProposta"(controller: "proposta", action:"simulaProposta")
        "/integracaoGetBanco"(controller: "proposta", action:"integracaoGetBanco")
        "/integracaoSaveBanco"(controller: "proposta", action:"integracaoSaveBanco")
        "/cep"(controller: "proposta", action:"consultaCEP")
    }
}
