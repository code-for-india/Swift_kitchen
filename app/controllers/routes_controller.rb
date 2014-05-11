require 'net/http'

class RoutesController < ApplicationController
  before_action :set_route, only: [:show, :edit, :update, :destroy,:optimizeRoute]
  before_action :set_route_from_device, only: [:optimize]
  before_action :authenticate_admin! ,except:[:optimizeRoute,:optimize]
  # GET /routes
  # GET /routes.json
  def index
    @routes = Route.all
  end

  # GET /routes/1
  # GET /routes/1.json
  def show
  end

  # GET /routes/new
  def new
    @route = Route.new
  end

  # GET /routes/1/edit
  def edit
  end

  # POST /routes
  # POST /routes.json
  def create
    @route = Route.new(route_params)

    respond_to do |format|
      if @route.save
        format.html { redirect_to @route, notice: 'Route was successfully created.' }
        format.json { render :show, status: :created, location: @route }
      else
        format.html { render :new }
        format.json { render json: @route.errors, status: :unprocessable_entity }
      end
    end
  end

  # PATCH/PUT /routes/1
  # PATCH/PUT /routes/1.json
  def update
    respond_to do |format|
      if @route.update(route_params)
        format.html { redirect_to @route, notice: 'Route was successfully updated.' }
        format.json { render :show, status: :ok, location: @route }
      else
        format.html { render :edit }
        format.json { render json: @route.errors, status: :unprocessable_entity }
      end
    end
  end

  # DELETE /routes/1
  # DELETE /routes/1.json
  def destroy
    @route.destroy
    respond_to do |format|
      format.html { redirect_to routes_url, notice: 'Route was successfully destroyed.' }
      format.json { head :no_content }
    end
  end

  def optimizeRoute
    puts @route.id
    schools = @route.schools.reverse
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
    response = JSON.parse(response.body)
    waypoint_order = response["routes"][0]["waypoint_order"]
#    puts waypoint_order
    a=[]
    x=0
    waypoint_order.each do |i|
      a[x] = schools[i]
      x = x+1
    end
    render json: a
  end

  def optimize
    schools = @route.schools.reverse
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

    waypoint_order = response["routes"][0]["waypoint_order"]
#    puts waypoint_order
    a=[]
    test = {}
    x=0
    waypoint_order.each do |i|
      a[x] = schools[i]
      x = x+1
    end
    test["routes"] = a
    test["route_name"] = @route.name
    test["kitchen"] = kitchen
    render json: test
  end



  private
    # Use callbacks to share common setup or constraints between actions.
    def set_route_from_device
      driver = Driver.find_by_device_id(params[:device_id])
      @route = driver.route
    end

    def set_route
      @route = Route.find(params[:id])
    end

    # Never trust parameters from the scary internet, only allow the white list through.
    def route_params
      params.require(:route).permit(:name, :route_code, :kitchen_id)
    end

    def path_with_params(path, params)
#       encoded_params = URI.encode_www_form(params)
       retVal = [path, params].join("&")

       return retVal
    end    
end
