json.array!(@routes) do |route|
  json.extract! route, :id, :name, :route_code, :kitchen_id
  json.url route_url(route, format: :json)
end
