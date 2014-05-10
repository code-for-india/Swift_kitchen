class Driver < ActiveRecord::Base
  belongs_to :kitchen
  belongs_to :route
end
