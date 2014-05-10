class AddLocationsToKitchens < ActiveRecord::Migration
  def change
    add_column :kitchens, :longitude, :float
    add_column :kitchens, :latitude, :float
  end
end
