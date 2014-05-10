class Kitchen < ActiveRecord::Base
  has_many :routes, dependent: :destroy
  #validates_presence_of :kitchen
end
