class CreateRoutes < ActiveRecord::Migration
  def change
    create_table :routes do |t|
      t.string :name
      t.string :route_code
      t.references :kitchen, index: true

      t.timestamps
    end
  end
end
