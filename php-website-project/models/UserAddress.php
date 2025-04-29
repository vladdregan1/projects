<?php

namespace app\models;

use Yii;

/**
 * This is the model class for table "user_address".
 *
 * @property int $id_user_address
 * @property string $country
 * @property string $county
 * @property string $city
 * @property string $street
 * @property string|null $block
 * @property string|null $entrance
 * @property string|null $apartment
 * @property string $type
 * @property int $id_user
 *
 * @property User $user
 */
class UserAddress extends \yii\db\ActiveRecord
{


    /**
     * {@inheritdoc}
     */
    public static function tableName()
    {
        return 'user_address';
    }

    /**
     * {@inheritdoc}
     */
    public function rules()
    {
        return [
            [['block', 'entrance', 'apartment'], 'default', 'value' => null],
            [['country', 'county', 'city', 'street', 'type', 'id_user'], 'required'],
            [['id_user'], 'integer'],
            [['country', 'county', 'city', 'street', 'block', 'entrance', 'apartment', 'type'], 'string', 'max' => 50],
            [['id_user'], 'exist', 'skipOnError' => true, 'targetClass' => User::class, 'targetAttribute' => ['id_user' => 'id_user']],
        ];
    }

    /**
     * {@inheritdoc}
     */
    public function attributeLabels()
    {
        return [
            'id_user_address' => 'Id User Address',
            'country' => 'Country',
            'county' => 'County',
            'city' => 'City',
            'street' => 'Street',
            'block' => 'Block',
            'entrance' => 'Entrance',
            'apartment' => 'Apartment',
            'type' => 'Type',
            'id_user' => 'Id User',
        ];
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
