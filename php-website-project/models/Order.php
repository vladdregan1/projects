<?php

namespace app\models;

use Yii;

/**
 * This is the model class for table "order".
 *
 * @property int $id_order
 * @property string $creation_date
 * @property float $price
 * @property int $qty
 * @property int $id_user
 * @property int|null $id_quote
 * @property int|null $id_status
 *
 * @property OrderAddress[] $orderAddresses
 * @property Payment[] $payments
 * @property Quote $quote
 * @property OrderStatus $status
 * @property User $user
 */
class Order extends \yii\db\ActiveRecord
{


    /**
     * {@inheritdoc}
     */
    public static function tableName()
    {
        return 'order';
    }

    /**
     * {@inheritdoc}
     */
    public function rules()
    {
        return [
            [['id_quote', 'id_status'], 'default', 'value' => null],
            [['creation_date', 'price', 'qty', 'id_user'], 'required'],
            [['creation_date'], 'safe'],
            [['price'], 'number'],
            [['qty', 'id_user', 'id_quote', 'id_status'], 'integer'],
            [['id_status'], 'exist', 'skipOnError' => true, 'targetClass' => OrderStatus::class, 'targetAttribute' => ['id_status' => 'id_status']],
            [['id_quote'], 'exist', 'skipOnError' => true, 'targetClass' => Quote::class, 'targetAttribute' => ['id_quote' => 'id_quote']],
            [['id_user'], 'exist', 'skipOnError' => true, 'targetClass' => User::class, 'targetAttribute' => ['id_user' => 'id_user']],
        ];
    }

    /**
     * {@inheritdoc}
     */
    public function attributeLabels()
    {
        return [
            'id_order' => 'Id Order',
            'creation_date' => \Yii::t('app', 'creation date'),
            'price' => \Yii::t('app', 'price'),
            'qty' => \Yii::t('app', 'qty'),
            'id_user' => \Yii::t('app', 'id user'),
            'id_quote' => \Yii::t('app', 'id quote'),
            'id_status' => 'Id Status',
        ];
    }

    /**
     * Gets query for [[OrderAddresses]].
     *
     * @return \yii\db\ActiveQuery
     */
    public function getOrderAddresses()
    {
        return $this->hasMany(OrderAddress::class, ['id_order' => 'id_order']);
    }

    /**
     * Gets query for [[Payments]].
     *
     * @return \yii\db\ActiveQuery
     */
    public function getPayments()
    {
        return $this->hasMany(Payment::class, ['id_order' => 'id_order']);
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

    /**
     * Gets query for [[Status]].
     *
     * @return \yii\db\ActiveQuery
     */
    public function getStatus()
    {
        return $this->hasOne(OrderStatus::class, ['id_status' => 'id_status']);
    }

    /**
     * Gets query for [[User]].
     *
     * @return \yii\db\ActiveQuery
     */
    public function getUser()
    {
        return $this->hasOne(User::class, ['id_user' => 'id_user']);
    }

}
