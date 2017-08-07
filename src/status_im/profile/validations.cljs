(ns status-im.profile.validations
  (:require [cljs.spec.alpha :as spec]
            [clojure.string :as string]
            [status-im.chat.constants :as chat.constants]
            [status-im.constants :as constants]
            [status-im.utils.homoglyph :as homoglyph]))

(defn correct-name? [username]
  (when-let [username (some-> username (string/trim))]
    (every? false?
            [(string/blank? username)
             (homoglyph/matches username constants/console-chat-id)
             (homoglyph/matches username constants/wallet-chat-id)
             (string/includes? username chat.constants/command-char)
             (string/includes? username chat.constants/bot-char)])))

(defn correct-email? [email]
  (let [pattern #"[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?"]
    (or (string/blank? email)
        (and (string? email) (re-matches pattern email)))))

(s/def ::name correct-name?)
(s/def ::email correct-email?)

(s/def ::profile (spec/keys :req-un [::name]))
