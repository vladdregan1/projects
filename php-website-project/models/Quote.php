<?php

namespace app\models;

use Yii;

/**
 * This is the model class for table "quote".
 *
 * @property int $id_quote
 * @property float $price
 * @property int $qty
 * @property int $id_product
 * @property int $id_user
 *
 * @property Order[] $orders
 * @property Product $product
 * @property QuoteAddress[] $quoteAddresses
 * @property User $user
 */
class Quote extends \yii\db\ActiveRecord
{


    /**
     * {@inheritdoc}
     */
    public static function tableName()
    {
        return 'quote';
    }

    /**
     * {@inheritdoc}
     */
    public function rules()
    {
        return [
            [['price', 'qty', 'id_product', 'id_user'], 'required'],
            [['price'], 'number'],
            [['qty', 'id_product', 'id_user'], 'integer'],
            [['id_product'], 'exist', 'skipOnError' => true, 'targetClass' => Product::class, 'targetAttribute' => ['id_product' => 'id_product']],
            [['id_user'], 'exist', 'skipOnError' => true, 'targetClass' => User::class, 'targetAttribute' => ['id_user' => 'id_user']],
        ];
    }

    /**
     * {@inheritdoc}
     */
    public function attributeLabels()
    {
        return [
            'id_quote' => 'Id Quote',
            'price' => 'Price',
            'qty' => 'Qty',
            'id_product' => 'Id Product',
            'id_user' => 'Id User',
        ];
    }

    /**
     * Gets query for [[Orders]].
     *
     * @return \yii\db\ActiveQuery
     */
    public function getOrders()
    {
        return $this->hasMany(Order::class, ['id_quote' => 'id_quote']);
    }

    /**
     * Gets query for [[Product]].
     *
     * @return \yii\db\ActiveQuery
     */
    public function getProduct()
    {
        return $this->hasOne(Product::class, ['id_product' => 'id_product']);
    }

    /**
     * Gets query for [[QuoteAddresses]].
     *
     * @return \yii\db\ActiveQuery
     */
    public function getQuoteAddresses()
    {
        return $this->hasMany(QuoteAddress::class, ['id_quote' => 'id_quote']);
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
