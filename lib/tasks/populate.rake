require 'csv'

namespace :create do
  desc "create routes"
  task :routes => :environment do
    file = "db/routes.csv" 
    puts "******* creating states******"
    CSV.foreach(file, :headers => true) do |row|
      a = Route.create!(row.to_hash)
      puts a.inspect
    end
  end


  namespace :create do
    desc "create schools"
    task :schools => :environment do
      file = "db/schools.csv" 
      puts "******* creating states******"
      CSV.foreach(file, :headers => true) do |row|
        a = School.create!(row.to_hash)
        puts a.inspect
      end
    end
  end
end


