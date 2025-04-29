<?php

namespace app\models;

use Yii;

/**
 * This is the model class for table "user".
 *
 * @property int $id_user
 * @property string $user_name
 * @property string $password
 * @property string $first_name
 * @property string $second_name
 * @property string $email
 * @property string $phone_number
 *
 * @property Order[] $orders
 * @property Quote[] $quotes
 * @property UserAddress[] $userAddresses
 */
class User extends \yii\db\ActiveRecord
{


    /**
     * {@inheritdoc}
     */
    public static function tableName()
    {
        return 'user';
    }

    /**
     * {@inheritdoc}
     */
    public function rules()
    {
        return [
            [['user_name', 'password', 'first_name', 'second_name', 'email', 'phone_number'], 'required'],
            [['user_name', 'password', 'first_name', 'second_name', 'email', 'phone_number'], 'string', 'max' => 50],
        ];
    }

    /**
     * {@inheritdoc}
     */
    public function attributeLabels()
    {
        return [
            'id_user' => 'Id User',
            'user_name' => 'User Name',
            'password' => 'Password',
            'first_name' => 'First Name',
            'second_name' => 'Second Name',
            'email' => 'Email',
            'phone_number' => 'Phone Number',
        ];
    }

    /**
     * Gets query for [[Orders]].
     *
     * @return \yii\db\ActiveQuery
     */
    public function getOrders()
    {
        return $this->hasMany(Order::class, ['id_user' => 'id_user']);
    }

    /**
     * Gets query for [[Quotes]].
     *
     * @return \yii\db\ActiveQuery
     */
    public function getQuotes()
    {
        return $this->hasMany(Quote::class, ['id_user' => 'id_user']);
    }

    /**
     * Gets query for [[UserAddresses]].
     *
     * @return \yii\db\ActiveQuery
     */
    public function getUserAddresses()
    {
        return $this->hasMany(UserAddress::class, ['id_user' => 'id_user']);
    }

}
