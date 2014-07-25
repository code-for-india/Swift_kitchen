class DashboardController < ApplicationController
  before_action :authenticate_admin!
  def index
  	@route = Route.second()
    @kitchen = @route.kitchen
#    path = "https://maps.googleapis.com/maps/api/directions/json?"
#    path += "origin="+@kitchen.latitude.to_s+","+@kitchen.longitude.to_s
#    path += "&destination="+kitchen.latitude.to_s+","+kitchen.longitude.to_s
#    path += "&waypoints=optimize:true|"
    @schools = @route.schools
    @waypoints = []
    x = 0
    @schools.each do |school|
    	schoolLatLng = {}
    	schoolLatLng["lat"] = school.latitude
    	schoolLatLng["lng"] = school.longitude
    	@waypoints[x] = schoolLatLng
    	x = x + 1
    end

#      marker.lat school.latitude
#      marker.lng school.longitude
#      path += school.latitude.to_s+","+school.longitude.to_s+"|"
#  	end
#    path += "&key=AIzaSyAh6c9pSW1QOaRKT152xUdsIrF6-W6Pwns"
#    path += "&sensor=false"
#    puts path
#    path = "https://maps.googleapis.com/maps/api/directions/json?origin=13.045227,77.489358&destination=13.045227,77.489358&waypoints=optimize:true|13.009242,77.609743|13.053467,77.472150|13.043653,77.484085&sensor=false&key=AIzaSyAh6c9pSW1QOaRKT152xUdsIrF6-W6Pwns"
#    encoded_url = URI.encode(path)
#    puts encoded_url
#    uri = URI.parse(encoded_url)
#    response = Net::HTTP.get_response(uri)
#    puts response.body
#    @response = response.body
  end

  def optimize
  	@route = Route.second()
    schools = @route.schools
    kitchen = @route.kitchen
    path = "https://maps.googleapis.com/maps/api/directions/json?"
    path += "origin="+kitchen.latitude.to_s+","+kitchen.longitude.to_s
    path += "&destination="+kitchen.latitude.to_s+","+kitchen.longitude.to_s
    path += "&waypoints=optimize:true|"
    schools.each do |school|
      path += school.latitude.to_s+","+school.longitude.to_s+"|"
    end
    path += "&key=AIzaSyAh6c9pSW1QOaRKT152xUdsIrF6-W6Pwns"
    path += "&sensor=false"
    puts path
#    path = "https://maps.googleapis.com/maps/api/directions/json?origin=13.045227,77.489358&destination=13.045227,77.489358&waypoints=optimize:true|13.009242,77.609743|13.053467,77.472150|13.043653,77.484085&sensor=false&key=AIzaSyAh6c9pSW1QOaRKT152xUdsIrF6-W6Pwns"
    encoded_url = URI.encode(path)
#    puts encoded_url
    uri = URI.parse(encoded_url)
    response = Net::HTTP.get_response(uri)
    puts response.body
    response = JSON.parse(response.body)
    render json: response.body
  end

end
