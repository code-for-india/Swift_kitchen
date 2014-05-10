class CreateSchools < ActiveRecord::Migration
  def change
    create_table :schools do |t|
      t.string :name
      t.string :longitude
      t.string :latitude
      t.references :route, index: true

      t.timestamps
    end
  end
end
