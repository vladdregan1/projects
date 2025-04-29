<?php

namespace app\controllers;

use app\models\Identifier;
use app\models\Meniu;
use app\models\MeniuSearch;
use app\models\Product;
use app\models\ProductSearch;
use app\services\MenuService;
use yii\helpers\ArrayHelper;
use yii\web\Controller;
use yii\web\NotFoundHttpException;
use yii\filters\VerbFilter;

/**
 * MeniuController implements the CRUD actions for Meniu model.
 */
class MeniuController extends Controller
{
    /**
     * @inheritDoc
     */
    public function behaviors()
    {
        return array_merge(
            parent::behaviors(),
            [
                'verbs' => [
                    'class' => VerbFilter::className(),
                    'actions' => [
                        'delete' => ['POST'],
                    ],
                ],
            ]
        );
    }

    /**
     * Lists all Meniu models.
     *
     * @return string
     */
    public function actionIndex()
    {
        $searchModel = new MeniuSearch();
        $dataProvider = $searchModel->search($this->request->queryParams);

        return $this->render('index', [
            'searchModel' => $searchModel,
            'dataProvider' => $dataProvider,
        ]);
    }

    /**
     * Displays a single Meniu model.
     * @param int $id_meniu Id Meniu
     * @return string
     * @throws NotFoundHttpException if the model cannot be found
     */

    public function actionView($id_meniu)
    {
        return $this->render('view', [
            'model' => $this->findModel($id_meniu),
        ]);
    }

    /**
     * Creates a new Meniu model.
     * If creation is successful, the browser will be redirected to the 'view' page.
     * @return string|\yii\web\Response
     */

    public function actionToday()
    {
        $menuService = new MenuService();
        $menuItems = $menuService->getMenuItemsPerTypeAndToday();
        $today = date('Y-m-d');

        return $this->render('today', [
            'menuItems' => $menuItems,
            'today' => $today,
        ]);
    }

    public function actionCalendar()
    {
        $days = Meniu::find()->select('day')->all();
        return $this->render('calendar', ['days' => $days]);
    }

    public function actionCreate()
    {
        //$modelProduct->get
        $model = new Meniu();


        if ($this->request->isPost) {
            if ($model->load($this->request->post()) && $model->save()) {
                return $this->redirect(['view', 'id_meniu' => $model->id_meniu]);
            }
        } else {
            $model->loadDefaultValues();
        }

        $products = Product::find()->select(['id_product', 'product_name'])->asArray()->all();
        $productItems = ArrayHelper::map($products, 'id_product', 'product_name');

        $identifiers = Identifier::find()->select(['id_identifier', 'name'])->asArray()->all();
        $identifierItems = ArrayHelper::map($identifiers, 'id_identifier', 'name');

        return $this->render('create', [
            'model' => $model,
            //'products' => $products,
            'productItems' => $productItems,
            'identifierItems' => $identifierItems,
        ]);
    }

    /**
     * Updates an existing Meniu model.
     * If update is successful, the browser will be redirected to the 'view' page.
     * @param int $id_meniu Id Meniu
     * @return string|\yii\web\Response
     * @throws NotFoundHttpException if the model cannot be found
     */
    public function actionUpdate($id_meniu)
    {
        $model = $this->findModel($id_meniu);

        if ($this->request->isPost && $model->load($this->request->post()) && $model->save()) {
            return $this->redirect(['view', 'id_meniu' => $model->id_meniu]);
        }

        $products = Product::find()->select(['id_product', 'product_name'])->asArray()->all();
        $productItems = ArrayHelper::map($products, 'id_product', 'product_name');

        $identifiers = Identifier::find()->select(['id_identifier', 'name'])->asArray()->all();
        $identifierItems = ArrayHelper::map($identifiers, 'id_identifier', 'name');

        return $this->render('update', [
            'model' => $model,
            'productItems' => $productItems,
            'identifierItems' => $identifierItems,
        ]);
    }

    /**
     * Deletes an existing Meniu model.
     * If deletion is successful, the browser will be redirected to the 'index' page.
     * @param int $id_meniu Id Meniu
     * @return \yii\web\Response
     * @throws NotFoundHttpException if the model cannot be found
     */
    public function actionDelete($id_meniu)
    {
        $this->findModel($id_meniu)->delete();

        return $this->redirect(['index']);
    }

    /**
     * Finds the Meniu model based on its primary key value.
     * If the model is not found, a 404 HTTP exception will be thrown.
     * @param int $id_meniu Id Meniu
     * @return Meniu the loaded model
     * @throws NotFoundHttpException if the model cannot be found
     */
    protected function findModel($id_meniu)
    {
        if (($model = Meniu::findOne(['id_meniu' => $id_meniu])) !== null) {
            return $model;
        }

        throw new NotFoundHttpException('The requested page does not exist.');
    }
}
