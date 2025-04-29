<?php

namespace app\models;

use Yii;

/**
 * This is the model class for table "order_address".
 *
 * @property int $id_order_address
 * @property string $country
 * @property string $county
 * @property string $city
 * @property string $street
 * @property string|null $block
 * @property string|null $entrance
 * @property string|null $apartment
 * @property string $type
 * @property int $id_order
 *
 * @property Order $order
 */
class OrderAddress extends \yii\db\ActiveRecord
{


    /**
     * {@inheritdoc}
     */
    public static function tableName()
    {
        return 'order_address';
    }

    /**
     * {@inheritdoc}
     */
    public function rules()
    {
        return [
            [['block', 'entrance', 'apartment'], 'default', 'value' => null],
            [['country', 'county', 'city', 'street', 'type', 'id_order'], 'required'],
            [['id_order'], 'integer'],
            [['country', 'county', 'city', 'street', 'block', 'entrance', 'apartment', 'type'], 'string', 'max' => 50],
            [['id_order'], 'exist', 'skipOnError' => true, 'targetClass' => Order::class, 'targetAttribute' => ['id_order' => 'id_order']],
        ];
    }

    /**
     * {@inheritdoc}
     */
    public function attributeLabels()
    {
        return [
            'id_order_address' => 'Id Order Address',
            'country' => 'Country',
            'county' => 'County',
            'city' => 'City',
            'street' => 'Street',
            'block' => 'Block',
            'entrance' => 'Entrance',
            'apartment' => 'Apartment',
            'type' => 'Type',
            'id_order' => 'Id Order',
        ];
    }

    /**
     * Gets query for [[Order]].
     *
     * @return \yii\db\ActiveQuery
     */
    public function getOrder()
    {
        return $this->hasOne(Order::class, ['id_order' => 'id_order']);
    }

}
