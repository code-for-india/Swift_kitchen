require 'csv'

namespace :create do
  desc "create routes"
  task :routes => :environment do
    file = "db/routes.csv" 
    puts "******* creating routes******"
    CSV.foreach(file, :headers => true) do |row|
      a = Route.create!(row.to_hash)
      puts a.inspect
    end
  end

  desc "create schools"
  task :schools => :environment do
    file = "db/schools.csv" 
    puts "******* creating schools******"
    CSV.foreach(file, :headers => true) do |row|
      a = School.create!(row.to_hash)
      puts a.inspect
    end
  end
end


namespace :db do
  task :populate => ['create:routes', 'create:schools']
end
