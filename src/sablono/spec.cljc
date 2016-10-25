(ns sablono.spec
  (:require [clojure.spec :as s]
            [clojure.spec.gen :as gen]))

(s/def ::html-type
  #{:a
    :abbr
    :address
    :area
    :article
    :aside
    :audio
    :b
    :base
    :bdi
    :bdo
    :big
    :blockquote
    :body
    :br
    :button
    :canvas
    :caption
    :circle
    :cite
    :clipPath
    :code
    :col
    :colgroup
    :data
    :datalist
    :dd
    :defs
    :del
    :details
    :dfn
    :dialog
    :div
    :dl
    :dt
    :ellipse
    :em
    :embed
    :fieldset
    :figcaption
    :figure
    :footer
    :form
    :g
    :h1
    :h2
    :h3
    :h4
    :h5
    :h6
    :head
    :header
    :hr
    :html
    :i
    :iframe
    :img
    :ins
    :kbd
    :keygen
    :label
    :legend
    :li
    :line
    :linearGradient
    :link
    :main
    :map
    :mark
    :mask
    :menu
    :menuitem
    :meta
    :meter
    :nav
    :noscript
    :object
    :ol
    :optgroup
    :output
    :p
    :param
    :path
    :pattern
    :picture
    :polygon
    :polyline
    :pre
    :progress
    :q
    :radialGradient
    :rect
    :rp
    :rt
    :ruby
    :s
    :samp
    :script
    :section
    :small
    :source
    :span
    :stop
    :strong
    :style
    :sub
    :summary
    :sup
    :svg
    :table
    :tbody
    :td
    :text
    :tfoot
    :th
    :thead
    :time
    :title
    :tr
    :track
    :tspan
    :u
    :ul
    :use
    :var
    :video
    :wbr})

(s/def ::html-attributes map?)

(s/def ::html-sequence
  (s/cat
   :type ::html-type
   :attrs ::html-attributes
   :children (s/spec (s/* ::html-content))))

(defn html-element?
  "Returns true if `x` is an HTML element."
  [x]
  (and (vector? x) (keyword? (first x))))

(s/def ::html-element
  (s/with-gen html-element?
    #(gen/fmap vec (s/gen ::html-sequence))))

(s/def ::html-content
  (s/or
   :string string?
   :number number?
   :element ::html-element))

(gen/sample (s/gen ::html-element) 10)

(comment
  (gen/fmap vec (s/gen ::html-sequence))
  (gen/sample (s/gen ::html-content))
  (gen/sample (gen/fmap vec (s/gen ::html-sequence)) 10)
  (gen/sample (s/gen ::html-sequence) 2)
  (gen/sample (s/gen ::html-content) 5)

  (gen/sample (s/gen ::html-element) 5)
  (s/exercise ::html-element 1))