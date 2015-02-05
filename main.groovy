import org.ccil.cowan.tagsoup.Parser
def slurper = new XmlSlurper(new Parser())  
def urls = ["http://steamcommunity.com/market/listings/753/113200-II%20The%20High%20Priestess",
        "http://steamcommunity.com/market/listings/753/113200-0%20The%20Fool",
        "http://steamcommunity.com/market/listings/753/113200-IV%20The%20Emperor",
        "http://steamcommunity.com/market/listings/753/113200-VIII%20Justice"]
def result = []

//para cada url
urls.each{ address ->
    def html = slurper.parse(address)

  //  println "_____________________________________________________________________________________________________________"
  //  println html
    html.body.'**'.find {
        it.@class.text()  == 'market_listing_price market_listing_price_with_fee'
    }*.each {value ->
        def input = [:]
        def price = value.text()
        input['url']  = address
        input['price'] = price.replaceAll(",",".").findAll(/-?\d+\.\d*|-?\d*\.\d+|-?\d+/ ).first()
        result.add(input)
    }
}

print "Precio" + "          "
println "Direccion"
 
result.each{ input ->
    print input['price'] + "          "
    println input['url']
}    
  
