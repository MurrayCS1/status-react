(ns status-im.profile.db
  (:require [cljs.spec.alpha :as spec]))

;EDIT PROFILE
(spec/def :profile/profile-edit (spec/nilable map?))
