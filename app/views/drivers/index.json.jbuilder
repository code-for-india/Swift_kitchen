json.array!(@drivers) do |driver|
  json.extract! driver, :id, :device_id, :name, :phone, :kitchen_id, :route_id
  json.url driver_url(driver, format: :json)
end
