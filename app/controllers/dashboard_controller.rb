class DashboardController < ApplicationController
  def index
  	@schools = School.all
	@hash = Gmaps4rails.build_markers(@schools) do |school, marker|
	  marker.lat school.longitude
	  marker.lng school.latitude
	end
  end
end
