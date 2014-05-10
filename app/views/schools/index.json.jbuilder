json.array!(@schools) do |school|
  json.extract! school, :id, :name, :longitude, :latitude, :route_id
  json.url school_url(school, format: :json)
end
