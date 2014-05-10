class CreateDrivers < ActiveRecord::Migration
  def change
    create_table :drivers do |t|
      t.string :device_id
      t.string :name
      t.string :phone
      t.references :kitchen, index: true
      t.references :route, index: true

      t.timestamps
    end
  end
end
