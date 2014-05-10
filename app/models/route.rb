class Route < ActiveRecord::Base
  belongs_to :kitchen
  has_many :schools, dependent: :destroy
  has_one :driver
end
