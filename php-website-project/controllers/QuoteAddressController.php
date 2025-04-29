<?php

namespace app\controllers;

use app\models\QuoteAddress;
use app\models\QuoteAddressSearch;
use yii\web\Controller;
use yii\web\NotFoundHttpException;
use yii\filters\VerbFilter;

/**
 * QuoteAddressController implements the CRUD actions for QuoteAddress model.
 */
class QuoteAddressController extends Controller
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
     * Lists all QuoteAddress models.
     *
     * @return string
     */
    public function actionIndex()
    {
        $searchModel = new QuoteAddressSearch();
        $dataProvider = $searchModel->search($this->request->queryParams);

        return $this->render('index', [
            'searchModel' => $searchModel,
            'dataProvider' => $dataProvider,
        ]);
    }

    /**
     * Displays a single QuoteAddress model.
     * @param int $id_quote_address Id Quote Address
     * @return string
     * @throws NotFoundHttpException if the model cannot be found
     */
    public function actionView($id_quote_address)
    {
        return $this->render('view', [
            'model' => $this->findModel($id_quote_address),
        ]);
    }

    /**
     * Creates a new QuoteAddress model.
     * If creation is successful, the browser will be redirected to the 'view' page.
     * @return string|\yii\web\Response
     */
    public function actionCreate()
    {
        $model = new QuoteAddress();

        if ($this->request->isPost) {
            if ($model->load($this->request->post()) && $model->save()) {
                return $this->redirect(['view', 'id_quote_address' => $model->id_quote_address]);
            }
        } else {
            $model->loadDefaultValues();
        }

        return $this->render('create', [
            'model' => $model,
        ]);
    }

    /**
     * Updates an existing QuoteAddress model.
     * If update is successful, the browser will be redirected to the 'view' page.
     * @param int $id_quote_address Id Quote Address
     * @return string|\yii\web\Response
     * @throws NotFoundHttpException if the model cannot be found
     */
    public function actionUpdate($id_quote_address)
    {
        $model = $this->findModel($id_quote_address);

        if ($this->request->isPost && $model->load($this->request->post()) && $model->save()) {
            return $this->redirect(['view', 'id_quote_address' => $model->id_quote_address]);
        }

        return $this->render('update', [
            'model' => $model,
        ]);
    }

    /**
     * Deletes an existing QuoteAddress model.
     * If deletion is successful, the browser will be redirected to the 'index' page.
     * @param int $id_quote_address Id Quote Address
     * @return \yii\web\Response
     * @throws NotFoundHttpException if the model cannot be found
     */
    public function actionDelete($id_quote_address)
    {
        $this->findModel($id_quote_address)->delete();

        return $this->redirect(['index']);
    }

    /**
     * Finds the QuoteAddress model based on its primary key value.
     * If the model is not found, a 404 HTTP exception will be thrown.
     * @param int $id_quote_address Id Quote Address
     * @return QuoteAddress the loaded model
     * @throws NotFoundHttpException if the model cannot be found
     */
    protected function findModel($id_quote_address)
    {
        if (($model = QuoteAddress::findOne(['id_quote_address' => $id_quote_address])) !== null) {
            return $model;
        }

        throw new NotFoundHttpException('The requested page does not exist.');
    }
}
