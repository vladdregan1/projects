<?php

namespace app\models;

use Yii;

/**
 * This is the model class for table "quote_address".
 *
 * @property int $id_quote_address
 * @property string $country
 * @property string $county
 * @property string $city
 * @property string $street
 * @property string|null $block
 * @property string|null $entrance
 * @property string|null $apartment
 * @property string $type
 * @property int $id_quote
 *
 * @property Quote $quote
 */
class QuoteAddress extends \yii\db\ActiveRecord
{


    /**
     * {@inheritdoc}
     */
    public static function tableName()
    {
        return 'quote_address';
    }

    /**
     * {@inheritdoc}
     */
    public function rules()
    {
        return [
            [['block', 'entrance', 'apartment'], 'default', 'value' => null],
            [['country', 'county', 'city', 'street', 'type', 'id_quote'], 'required'],
            [['id_quote'], 'integer'],
            [['country', 'county', 'city', 'street', 'block', 'entrance', 'apartment', 'type'], 'string', 'max' => 50],
            [['id_quote'], 'exist', 'skipOnError' => true, 'targetClass' => Quote::class, 'targetAttribute' => ['id_quote' => 'id_quote']],
        ];
    }

    /**
     * {@inheritdoc}
     */
    public function attributeLabels()
    {
        return [
            'id_quote_address' => 'Id Quote Address',
            'country' => 'Country',
            'county' => 'County',
            'city' => 'City',
            'street' => 'Street',
            'block' => 'Block',
            'entrance' => 'Entrance',
            'apartment' => 'Apartment',
            'type' => 'Type',
            'id_quote' => 'Id Quote',
        ];
    }

    /**
     * Gets query for [[Quote]].
     *
     * @return \yii\db\ActiveQuery
     */
    public function getQuote()
    {
        return $this->hasOne(Quote::class, ['id_quote' => 'id_quote']);
    }

}
