class Kitchen < ActiveRecord::Base
  has_many :routes, dependent: :destroy
  has_many :drivers, dependent: :destroy
  #validates_presence_of :kitchen
end
